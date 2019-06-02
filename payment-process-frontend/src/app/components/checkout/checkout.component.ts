import { Card } from './../../model/card.model';
import { CardService } from './../../services/card.service';
import { Cart } from './../../model/cart.model';
import { Payment } from './../../model/payment.model';
import { PaymentService } from './../../services/payment.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ResponseApi } from 'src/app/model/response-api';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  @ViewChild("form")
  form: NgForm

  payment = new Payment();
  cart = new Cart(12, 10, 22, 5, 47); //default values
  message: string;
  selectedCard: boolean;
  validCard: boolean;
  success: boolean;
  error: boolean;

  constructor(
    private paymentService: PaymentService,
    private cardService: CardService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.success = false;
    this.error = false;
    this.validCard = false;
    this.message = "";
  }

  create() {
    this.message = "";
    if (this.payment.type == "BOLETO") {
      this.payment.card = null;
    }

    this.payment.amount = this.cart.total;
    this.paymentService.create(this.payment).subscribe((responseApi: ResponseApi) => {

      let paymentRet: Payment = responseApi.data;
      this.message = 'Payment sent with sucess.';
      this.success = true;
      this.error = false;
      this.validCard = false;

      this.form.resetForm();
      this.payment = new Payment();
    }, err => {
      this.error = true;
      this.success = false;
      this.validCard = false;
      this.showError(err);
    });
  }

  randomCartValues() {
    let p1 = Math.floor((Math.random() * 100) + 10);
    let p2 = Math.floor((Math.random() * 100) + 10);
    let p3 = Math.floor((Math.random() * 100) + 10);
    let promo = Math.floor((Math.random() * 100) + 1);
    let total = (p1 + p2 + p3) - promo;
    this.cart = new Cart(p1, p2, p3, promo, total);
    this.payment.amount = total;
  }

  radioChange() {

    if (this.payment.type == "CREDIT_CARD") {
      this.selectedCard = true;
    } else {
      this.selectedCard = false;
      this.validCard = false;
    }
  }

  validateCard() {
    this.cardService.validate(this.payment.card.number).subscribe((responseApi: ResponseApi) => {

      let cardRet: Card = responseApi.data;
      this.payment.card.valid = cardRet.valid;
      this.payment.card.brand = cardRet.brand;
      this.validCard = true;

    }, err => {
      this.showError(err);
    });
  }

  getFromGroupClass(isInvalid: boolean, isDirty): {}{
    return {
      'form-group': true,
      'has-error' : isInvalid && isDirty,
      'has-success' : isInvalid && isDirty
    };
  }

  showError(err: any){
    if(err['error'] != null) {
      if( err['error']['errors'] != null) {
        this.message =  err['error']['errors'][0];
      } else {
        this.message =  err['error']['message'];
      } 

    } else {
      this.message = err['message'];
    }
  } 
}



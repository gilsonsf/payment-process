import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/services/payment.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/response-api';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.css']
})
export class PaymentHistoryComponent implements OnInit {

  paymentsHistory = [];

  constructor(
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    let id: string = this.route.snapshot.params['id'];
    if(id!= undefined){
      this.findPaymentHistoryById(id);
    }
  }

  findPaymentHistoryById(id:string) {
    this.paymentService.findPaymentHistoryById(id).subscribe((responseApi: ResponseApi) => {

      this.paymentsHistory = responseApi['data'];

    }, err => {
      console.log(err['message']);
    });
  }
}

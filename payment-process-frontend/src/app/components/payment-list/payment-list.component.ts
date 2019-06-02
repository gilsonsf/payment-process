import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/services/payment.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/response-api';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {

  payments =[];

  constructor(
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.paymentService.findAll().subscribe((responseApi: ResponseApi) => {

      this.payments = responseApi['data'];

    }, err => {
      console.log(err['message']);
    });
  }

  view(id:string){
    this.router.navigate(['/payment-view', id]);
  }

}

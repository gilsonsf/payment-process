import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/services/payment.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/response-api';
import { Payment } from 'src/app/model/payment.model';

@Component({
  selector: 'app-payment-view',
  templateUrl: './payment-view.component.html',
  styleUrls: ['./payment-view.component.css']
})
export class PaymentViewComponent implements OnInit {

  payment = new Payment();

  constructor(
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    let id: string = this.route.snapshot.params['id'];
    if(id!= undefined){
      this.findById(id);
    }
  }

  findById(id:string) {
    this.paymentService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.payment = responseApi['data'];
    }, err => {
      console.log(err['message']);
    });
  }

  viewHistory(){
    this.router.navigate(['/payment-history', this.payment.id]);
  }
 
}

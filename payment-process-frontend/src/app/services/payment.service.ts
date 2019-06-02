import { Payment } from './../model/payment.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PATH_API } from './service';


@Injectable({
    providedIn: 'root'
})
export class PaymentService {

    constructor(private http: HttpClient) { }

    create(payment: Payment) {
        return this.http.post(`${PATH_API}/payment`, payment);
    }

    findAll() {
        return this.http.get(`${PATH_API}/payment`);
    }

    findById(id: string) {
        return this.http.get(`${PATH_API}/payment/${id}`);
    }
    
    findPaymentHistoryById(id: string) {
        return this.http.get(`${PATH_API}/payment/history/${id}`);
    }

}

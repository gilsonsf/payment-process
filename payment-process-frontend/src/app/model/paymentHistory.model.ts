import { Card } from './card.model';
import { Client } from './client.model';
import { Buyer } from './buyer.model';
import { Payment } from './payment.model';

export class PaymentHistory {

    public id: number;
    public data: string;
    public payment: Payment;
    public status: string;

    constructor() {
    }
}
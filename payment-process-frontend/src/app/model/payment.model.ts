import { Card } from './card.model';
import { Client } from './client.model';
import { Buyer } from './buyer.model';

export class Payment {

    public id: number;
    public amount: number;
    public type: string;
    public status: string;
    public card: Card;
    public client: Client;
    public buyer: Buyer;

    constructor() {
        this.id = 0;
        this.amount = 0;
        this.type = "BOLETO";
        this.status = "CREATED";
        this.card = new Card();
        this.client = new Client();
        this.buyer = new Buyer();
    }
}
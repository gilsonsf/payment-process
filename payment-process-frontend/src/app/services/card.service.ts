import { Card } from './../model/card.model';
import { Payment } from '../model/payment.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PATH_API } from './service';


@Injectable({
    providedIn: 'root'
})
export class CardService {

    constructor(private http: HttpClient) { }

    validate(number: string) {
        return this.http.get(`${PATH_API}/card/validate/${number}`);
    }

}

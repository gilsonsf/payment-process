import { CardService } from './services/card.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { routes } from './app.routes';
import {APP_BASE_HREF} from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PaymentService } from './services/payment.service';
import { PaymentListComponent } from './components/payment-list/payment-list.component';
import { PaymentViewComponent } from './components/payment-view/payment-view.component';
import { PaymentHistoryComponent } from './components/payment-history/payment-history.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CheckoutComponent,
    PaymentListComponent,
    PaymentViewComponent,
    PaymentHistoryComponent
  ],
  imports: [
    BrowserModule,
    routes,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    PaymentService,
    CardService,
    {
      provide: APP_BASE_HREF, 
      useValue: '/'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

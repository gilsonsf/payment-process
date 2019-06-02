import { PaymentHistoryComponent } from './components/payment-history/payment-history.component';
import { PaymentViewComponent } from './components/payment-view/payment-view.component';
import { PaymentListComponent } from './components/payment-list/payment-list.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { HeaderComponent } from './components/header/header.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';


export const ROUTES: Routes = [
    { path : '', component: HeaderComponent},
    { path : 'checkout', component: CheckoutComponent},
    { path : 'payment-list', component: PaymentListComponent},
    { path : 'payment-view/:id', component: PaymentViewComponent},
    { path : 'payment-history/:id', component: PaymentHistoryComponent},
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);
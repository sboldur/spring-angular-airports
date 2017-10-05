import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {QueryComponent} from './query/query.component';
import {ReportComponent} from './report/report.component';
import {HttpModule} from "@angular/http";
import {ApiModuleRoutingModule} from "./api-module/api-module-routing.module";
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoadingModule} from "ngx-loading";
import {NavbarComponent} from './navbar/navbar.component';
import {AlertModule} from "ngx-bootstrap";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    QueryComponent,
    ReportComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    ApiModuleRoutingModule,
    BsDropdownModule.forRoot(),
    AlertModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    LoadingModule,
  ],
  exports:[BsDropdownModule],//so become available to other modules as well
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

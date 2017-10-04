import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { QueryComponent } from './query/query.component';
import { ReportComponent } from './report/report.component';
import {HttpModule} from "@angular/http";
import {ApiModuleRoutingModule} from "./api-module/api-module-routing.module";
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoadingModule} from "ngx-loading";
import { NavbarComponent } from './navbar/navbar.component';

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
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    LoadingModule
  ],
  exports:[BsDropdownModule, TooltipModule, ModalModule],//so become available to other modules as well
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

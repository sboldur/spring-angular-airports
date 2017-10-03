import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {QueryComponent} from "../query/query.component";
import {HomeComponent} from "../home/home.component";
import {ReportComponent} from "../report/report.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'query', component: QueryComponent},
  {path: 'reports', component: ReportComponent},
  {path: '**', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class ApiModuleRoutingModule { }

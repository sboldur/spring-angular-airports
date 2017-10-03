import { Injectable } from '@angular/core';
import {Headers, Response, Http} from "@angular/http";
import {Observable} from 'rxjs/Rx';
import {ReportResponse} from "../model/ReportResponse";

@Injectable()
export class ReportService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private reportUrl = 'http://localhost:8080/api/reports';

  constructor(private http: Http) { }

  getAllReports(): Observable<ReportResponse> {
    return this.http.get(`${this.reportUrl}`)
      .map((response: Response) => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

}

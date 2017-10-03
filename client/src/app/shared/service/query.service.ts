import { Injectable } from '@angular/core';
import {Headers, Response, Http} from "@angular/http";
import {Observable} from 'rxjs/Rx';
import {Airport} from "../model/Airport";

@Injectable()
export class QueryService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private queryUrl = 'http://localhost:8080/api/query';

  constructor(private http: Http) { }

  getAirportsByCountry(codeOrName: String): Observable<Airport[]> {
    return this.http.get(`${this.queryUrl}/${codeOrName}`)
      .map((response: Response) => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }

}

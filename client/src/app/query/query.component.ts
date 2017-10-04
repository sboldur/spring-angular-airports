import {Component, OnInit} from '@angular/core';
import {QueryService} from "../shared/service/query.service";
import {Airport} from "../shared/model/Airport";

@Component({
  selector: 'app-query',
  templateUrl: './query.component.html',
  styleUrls: ['./query.component.css'],
  providers: [QueryService]
})
export class QueryComponent implements OnInit {
  airportsWithRunways: Array<Airport>;
  loading = false;

  constructor(private queryService: QueryService) {
  }

  ngOnInit() {
  }

  getAirportsByCountry(codeOrName: String) {
    this.loading = true;
    this.queryService.getAirportsByCountry(codeOrName).subscribe(
      data => {
        this.airportsWithRunways = data;
        this.loading = false;
      },
      error => {
        console.log(error);
        this.loading = false;
      }
    )
  }

}

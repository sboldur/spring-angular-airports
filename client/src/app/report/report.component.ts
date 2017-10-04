import {Component, OnInit} from '@angular/core';
import {ReportService} from "../shared/service/report.service";
import {ReportResponse} from "../shared/model/ReportResponse";
import {Country} from "../shared/model/Country";
import {map} from "rxjs/operator/map";

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css'],
  providers: [ReportService]
})
export class ReportComponent implements OnInit {

  constructor(private reportService: ReportService) {
  }

  reportResponse: ReportResponse;
  objectKeys = Object.keys;
  loading = false;
  countries: Country[];

  ngOnInit() {
    this.loadAllReports();

  }

  loadAllReports() {
    this.loading = true;
    this.reportService.getAllReports().subscribe(
      data => {
        this.reportResponse = data;
        this.loading = false;
        this.getCountryWithSurfaceTypes();
      },
      error => {
        console.log(error);
        this.loading = false;
      }
    )
  }

  mapJsonToCountry(json:string){
    let countryPrototype = Object.create(Country.prototype);
    return JSON.parse(json, countryPrototype);
  }

  getCountryWithSurfaceTypes() {
    let countryPrototype = Object.create(Country.prototype);
    this.countries = Object.keys(this.reportResponse.runwaySurfacesPerCountry)
      .map((key) => {
        return JSON.parse(key, countryPrototype);
      });
  }

  mapCountryToJSon(country:Country){
    return JSON.stringify(country);

  }

}

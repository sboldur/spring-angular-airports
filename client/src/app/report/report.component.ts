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
  loading = false;
  countries: Country[];
  countryWithRunwaysPageItems: Array<Country>;
  pages : number;
  pageSize : number = 5;
  pageNumber : number = 0;
  currentIndex : number = 1;
  pagesIndex : Array<number>;
  pageStart : number = 1;

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
        this.init();
      },
      error => {
        console.log(error);
        this.loading = false;
      }
    )
  }

  init(){
    this.currentIndex = 1;
    this.pageStart = 1;
    this.pageSize = 5;
    this.pages = 1;

    this.pageNumber = parseInt(""+ (this.countries.length / this.pageSize));
    if(this.countries.length % this.pageSize != 0){
      this.pageNumber ++;
    }

    if(this.pageNumber  < this.pages){
      this.pages =  this.pageNumber;
    }
    this.refreshItems();
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

  refreshItems(){
    this.countryWithRunwaysPageItems = this.countries.slice((this.currentIndex - 1)*this.pageSize, (this.currentIndex) * this.pageSize);
    this.pagesIndex =  this.fillArray();
  }

  fillArray(): any{
    let obj = Array();
    for(let index = this.pageStart; index< this.pageStart + this.pages; index ++) {
      obj.push(index);
    }
    return obj;
  }

  prevPage(){
    if(this.currentIndex>1){
      this.currentIndex --;
    }
    if(this.currentIndex < this.pageStart){
      this.pageStart = this.currentIndex;
    }
    this.refreshItems();
  }

  nextPage(){
    if(this.currentIndex < this.pageNumber){
      this.currentIndex ++;
    }
    if(this.currentIndex >= (this.pageStart + this.pages)){
      this.pageStart = this.currentIndex - this.pages + 1;
    }
    this.refreshItems();
  }


}

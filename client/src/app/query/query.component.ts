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
  pageItems: Array<Airport>;
  loading = false;
  pages : number;
  pageSize : number = 5;
  pageNumber : number = 0;
  currentIndex : number = 1;
  pagesIndex : Array<number>;
  pageStart : number = 1;

  constructor(private queryService: QueryService) {
  }

  ngOnInit() {  }

  init(){
    this.currentIndex = 1;
    this.pageStart = 1;
    this.pageSize = 5;
    this.pages = 1;

    this.pageNumber = parseInt(""+ (this.airportsWithRunways.length / this.pageSize));
    if(this.airportsWithRunways.length % this.pageSize != 0){
      this.pageNumber ++;
    }

    if(this.pageNumber  < this.pages){
      this.pages =  this.pageNumber;
    }
    this.refreshItems();
  }

  getAirportsByCountry(codeOrName: String) {
    this.loading = true;
    this.queryService.getAirportsByCountry(codeOrName).subscribe(
      data => {
        this.airportsWithRunways = data;
        this.init();
        this.loading = false;
      },
      error => {
        console.log(error);
        this.loading = false;
      }
    )
  }

  refreshItems(){
    this.pageItems = this.airportsWithRunways.slice((this.currentIndex - 1)*this.pageSize, (this.currentIndex) * this.pageSize);
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

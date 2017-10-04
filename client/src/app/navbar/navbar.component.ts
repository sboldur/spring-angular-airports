import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent{
  isCollapsed = false;   // store state

  constructor() { }

  toggleState() { // click handler
    let bool = this.isCollapsed;
    this.isCollapsed = bool === false ? true : false;
  }

}

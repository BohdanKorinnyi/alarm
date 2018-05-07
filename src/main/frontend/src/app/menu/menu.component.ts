import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  clientClicked = true;
  callClicked = false;

  constructor(router: Router) {
    router.events.subscribe((url: any) => {
      if (url.url !== undefined && url.url === '/clients') {
        this.clientClicked = true;
        this.callClicked = false;
      } else if (url.url !== undefined && url.url === '/calls') {
        this.clientClicked = false;
        this.callClicked = true;
      }
    });
  }

  ngOnInit() {
  }

}

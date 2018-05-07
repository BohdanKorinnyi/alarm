import {Component, OnInit} from '@angular/core';
import {Alarm, Call} from '../model/call';
import {CallService} from '../service/call.service';
import {Pageable} from '../model/pageable';
import * as moment from 'moment';

@Component({
  selector: 'app-calls',
  templateUrl: './calls.component.html',
  styleUrls: ['./calls.component.css']
})
export class CallsComponent implements OnInit {

  constructor(private callService: CallService) {
  }

  DATE_PATTERN = 'DD/MM/YYYY HH:mm';
  calls: Call[];

  currentPage: number;
  pages: number[];

  ngOnInit() {
    this.callService.getCalls().subscribe((page: Pageable<Call>) => {
      this.calls = page.content;
      this.pages = Array(page.totalPages).fill(0);
    });
  }

  formatDate(date: string) {
    return date != null ? moment(date).format(this.DATE_PATTERN) : '';
  }

  formatDuration(duration: number) {
    return duration != null ? duration : 0;
  }

  formatCost(cost: number) {
    return cost != null ? cost * -1 : 0;
  }

  loadPage(page: number) {
    this.currentPage = page;
    this.callService.getCallsByPage(page).subscribe((newPage: Pageable<Call>) => {
      this.calls = newPage.content;
    });
  }

  formatLanguage(alarm: Alarm) {
    return alarm !== null ? alarm.language !== null ? alarm.language.name : '' : '';
  }
}

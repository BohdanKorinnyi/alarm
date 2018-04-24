import {Component, OnInit} from '@angular/core';
import {Client} from '../../../model/client';
import {ClientService} from '../../../service/client.service';
import {Pageable} from '../../../model/pageable';
import * as moment from 'moment';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  constructor(private clientService: ClientService) {
  }

  DATE_PATTERN = 'DD/MM/YYYY HH:MM';
  clients: Client[];

  ngOnInit() {
    this.clientService.getClients().subscribe((page: Pageable<Client>) => {
      this.clients = page.content;
    });
  }

  formatDate(date: string) {
    return date != null ? moment(date).format(this.DATE_PATTERN) : '';
  }
}

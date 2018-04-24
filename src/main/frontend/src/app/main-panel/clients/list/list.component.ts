import {Component, OnInit} from '@angular/core';
import {Client} from '../../../client';
import {ClientService} from '../../../client.service';
import {Pageable} from '../../../pageable';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  constructor(private clientService: ClientService) {
  }

  clients: Client[];

  ngOnInit() {
    this.clientService.getClients().subscribe((page: Pageable<Client>) => {
      this.clients = page.content;
    });
  }
}

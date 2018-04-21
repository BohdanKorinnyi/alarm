import {Component, OnInit} from '@angular/core';
import {Client} from '../../../client';
import {Pageable} from '../../../pageable';
import {ClientService} from '../../../client.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  constructor(private clientService: ClientService) {
  }

  clientTotal: number;

  ngOnInit() {
    this.clientService.getClients().subscribe((page: Pageable<Client>) => {
      console.log(page.content);
      this.clientTotal = page.totalElements;
    });
  }

}

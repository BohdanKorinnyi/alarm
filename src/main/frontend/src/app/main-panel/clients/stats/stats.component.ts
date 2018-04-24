import {Component, OnInit} from '@angular/core';
import {Client} from '../../../model/client';
import {Pageable} from '../../../model/pageable';
import {ClientService} from '../../../service/client.service';
import {CallService} from '../../../service/call.service';
import {CallStatus} from '../../../model/call';
import {Stats} from '../../../model/stats';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  constructor(private clientService: ClientService, private callService: CallService) {
  }

  clients = 0;
  completedCalls = 0;
  cost = 0;

  ngOnInit() {
    this.callService.getCallStatisticsByStatus(CallStatus.COMPLETED).subscribe((stats: Stats) => {
      this.completedCalls = stats.amount;
      this.cost = stats.cost;
    });
    this.clientService.getClients().subscribe((page: Pageable<Client>) => {
      this.clients = page.totalElements;
    });
  }
}

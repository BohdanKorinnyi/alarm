import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CallStatus} from '../model/call';

@Injectable()
export class CallService {

  constructor(private http: HttpClient) {
  }

  getCallStatisticsByStatus(status: CallStatus) {
    return this.http.get('/api/calls/statistics?id=' + status.valueOf());
  }
}

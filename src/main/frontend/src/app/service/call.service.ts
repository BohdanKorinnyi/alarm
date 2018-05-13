import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CallStatus} from '../model/call';

@Injectable()
export class CallService {

  constructor(private http: HttpClient) {
  }

  getCalls() {
    return this.http.get('/api/calls');
  }

  getCallsByPage(page: number) {
    return this.http.get('/api/calls?page=' + page);
  }

  getCallStatisticsByStatus(status: CallStatus) {
    return this.http.get('/api/calls/statistics?id=' + status.valueOf());
  }
}

import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {MenuComponent} from './menu/menu.component';
import {MainPanelComponent} from './main-panel/main-panel.component';
import {ClientService} from './service/client.service';
import {CallService} from './service/call.service';
import {HttpClientModule} from '@angular/common/http';
import {ListComponent} from './main-panel/clients/list/list.component';
import {StatsComponent} from './main-panel/clients/stats/stats.component';
import {RouterModule, Routes} from '@angular/router';
import {CallsComponent} from './calls/calls.component';

const routes: Routes = [
  {path: '', redirectTo: 'clients', pathMatch: 'full'},
  {path: 'clients', component: MainPanelComponent},
  {path: 'calls', component: CallsComponent},
  {path: '**', component: MainPanelComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    MainPanelComponent,
    StatsComponent,
    ListComponent,
    CallsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes, {useHash: false})
  ],
  providers: [ClientService, CallService],
  bootstrap: [AppComponent]
})
export class AppModule {

}

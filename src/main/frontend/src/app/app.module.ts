import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {MenuComponent} from './menu/menu.component';
import {MainPanelComponent} from './main-panel/main-panel.component';
import {ClientService} from './client.service';
import {AlarmService} from './alarm.service';
import {HttpClientModule} from '@angular/common/http';
import { ClientsComponent } from './main-panel/clients/clients.component';
import { ListComponent } from './main-panel/clients/list/list.component';
import {StatsComponent} from './main-panel/clients/stats/stats.component';
import {CommonModule} from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    MainPanelComponent,
    ClientsComponent,
    StatsComponent,
    ListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ClientService, AlarmService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

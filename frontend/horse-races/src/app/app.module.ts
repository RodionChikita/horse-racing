import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {
  DxButtonModule,
  DxCheckBoxModule,
  DxDataGridModule,
  DxDateBoxModule,
  DxPopupModule, DxSelectBoxModule,
  DxTextBoxModule
} from 'devextreme-angular';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { OwnerListComponent } from './owner/owner-list/owner-list.component';
import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { HorseListComponent } from './horse/horse-list/horse-list.component';
import { RaceComponent } from './race/race.component';
import {RaceResultComponent} from './race-result/race-result.component';
import { JockeyListComponent } from './jockey/jockey-list/jockey-list.component';

@NgModule({
  declarations: [
    AppComponent,
    OwnerListComponent,
    HorseListComponent,
    RaceComponent,
    RaceResultComponent,
    JockeyListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    DxDataGridModule,
    DxCheckBoxModule,
    ReactiveFormsModule,
    DxTextBoxModule,
    DxButtonModule,
    DxPopupModule,
    DxDateBoxModule,
    DxSelectBoxModule,
    AppRoutingModule
  ],
  providers: [OwnerListComponent, HorseListComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }

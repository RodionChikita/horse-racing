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

@NgModule({
  declarations: [
    AppComponent,
    OwnerListComponent
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
  providers: [OwnerListComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }

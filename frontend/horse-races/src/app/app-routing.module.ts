import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OwnerListComponent} from "./owner/owner-list/owner-list.component";

const routes: Routes = [
  { path: '', redirectTo: 'owner', pathMatch: 'full'},
  { path: 'owner', component: OwnerListComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OwnerListComponent} from "./owner/owner-list/owner-list.component";
import {HorseListComponent} from "./horse/horse-list/horse-list.component";
import {RaceComponent} from "./race/race.component";
import {RaceResultComponent} from "./race-result/race-result.component";
import {JockeyListComponent} from "./jockey/jockey-list/jockey-list.component";

const routes: Routes = [
  { path: '', redirectTo: 'owner', pathMatch: 'full'},
  { path: 'horse', component: HorseListComponent },
  { path: 'owner', component: OwnerListComponent },
  { path: 'jockey', component: JockeyListComponent },
  { path: 'race', component: RaceComponent },
  { path: 'race-results/:id', component: RaceResultComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
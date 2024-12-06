import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { OwnerListComponent } from './owner/owner-list/owner-list.component';
import { HorseListComponent } from './horse/horse-list/horse-list.component';
import { RaceComponent } from './race/race.component';
import { RaceResultComponent } from './race-result/race-result.component';
import { JockeyListComponent } from './jockey/jockey-list/jockey-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'owner', pathMatch: 'full' },
  { path: 'horse', component: HorseListComponent, canActivate: [AuthGuard], data: { roles: ['user'] } },
  { path: 'owner', component: OwnerListComponent, canActivate: [AuthGuard], data: { roles: ['user'] } },
  { path: 'jockey', component: JockeyListComponent, canActivate: [AuthGuard], data: { roles: ['user'] } },
  { path: 'race', component: RaceComponent, canActivate: [AuthGuard] },
  { path: 'race-results/:id', component: RaceResultComponent, canActivate: [AuthGuard], data: { roles: ['user'] } },
  { path: 'access-denied', component: AccessDeniedComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
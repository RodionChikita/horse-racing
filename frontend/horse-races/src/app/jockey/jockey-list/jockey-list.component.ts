import { Component, OnInit } from '@angular/core';
import {CreateOrUpdateJockeyDtoRq, JockeyDto} from "../jockey.models";
import {JockeyService} from "../jockey.service";

@Component({
  selector: 'app-owner-list',
  templateUrl: './jockey-list.component.html',
  styleUrls: ['./jockey-list.component.scss']
})
export class JockeyListComponent implements OnInit {
  jockeys: JockeyDto[] = [];

  constructor(private jockeyService: JockeyService) {}

  ngOnInit() {
    this.loadJockeys();
  }

  loadJockeys() {
    this.jockeyService.findAll().subscribe(data => {
      this.jockey = data;
    });
  }

  // Add a new jockey
  addJockey(e: any) {
    const newJockey: CreateOrUpdateJockeyDtoRq = e.data;
    this.jockeyService.insert(newJockey).subscribe(() => {
      this.loadJockeys();
    });
  }

  // Update an existing jockey
  updateJockey(e: any) {
    const updatedJockey: CreateOrUpdateJockeyDtoRq = { ...e.oldData, ...e.newData };
    this.jockeyService.update(updatedJockey).subscribe(() => {
      this.loadJockeys();
    });
  }

  // Delete an jockey by ID
  deleteJockey(e: any) {
    const jockeyId = e.data.id;
    this.jockeyService.deleteById(jockeyId).subscribe(() => {
      this.loadJockeys();
    });
  }
}
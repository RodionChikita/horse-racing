import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CreateOrUpdateHorseDtoRq, HorseDto} from "./horse.models";
import {RaceResultDto} from "../race-result/race-result.model";

@Injectable({
    providedIn: 'root'
})
export class HorseService {
    private baseUrl = 'http://0.0.0.0:8080/api/v1/horse';

    constructor(private http: HttpClient) {}

    findAll(): Observable<HorseDto[]> {
        return this.http.get<HorseDto[]>(`${this.baseUrl}`);
    }

    insert(horse: CreateOrUpdateHorseDtoRq): Observable<CreateOrUpdateHorseDtoRq> {
        return this.http.post<CreateOrUpdateHorseDtoRq>(`${this.baseUrl}`, horse);
    }

    update(horse: CreateOrUpdateHorseDtoRq): Observable<CreateOrUpdateHorseDtoRq> {
        return this.http.put<CreateOrUpdateHorseDtoRq>(`${this.baseUrl}`, horse);
    }

    deleteById(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }

    findAllHorseRaceResults(id: number): Observable<RaceResultDto[]> {
        return this.http.get<RaceResultDto[]>(`${this.baseUrl}/${id}/race_results`);
    }
}

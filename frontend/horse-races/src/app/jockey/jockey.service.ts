import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CreateOrUpdateJockeyDtoRq, JockeyDto} from "./jockey.models";

@Injectable({
    providedIn: 'root'
})
export class JockeyService {
    private apiUrl = '/api/v1/horse'

    constructor(private http: HttpClient) {}

    findAll(): Observable<JockeyDto[]> {
        return this.http.get<JockeyDto[]>(this.apiUrl);
    }

    insert(jockey: CreateOrUpdateJockeyDtoRq): Observable<CreateOrUpdateJockeyDtoRq> {
        return this.http.post<CreateOrUpdateJockeyDtoRq>(this.apiUrl, jockey);
    }

    update(jockey: CreateOrUpdateJockeyDtoRq): Observable<CreateOrUpdateJockeyDtoRq> {
        return this.http.put<CreateOrUpdateJockeyDtoRq>(this.apiUrl, jockey);
    }

    deleteById(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}

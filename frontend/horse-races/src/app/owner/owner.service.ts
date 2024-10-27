import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateOrUpdateOwnerDtoRq, OwnerDto } from './owner.models';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {
  private apiUrl = '/api/v1/owner';

  constructor(private http: HttpClient) {}

  findAll(): Observable<OwnerDto[]> {
    return this.http.get<OwnerDto[]>(this.apiUrl);
  }

  insert(owner: CreateOrUpdateOwnerDtoRq): Observable<OwnerDto> {
    return this.http.post<OwnerDto>(this.apiUrl, owner);
  }

  update(owner: CreateOrUpdateOwnerDtoRq): Observable<OwnerDto> {
    return this.http.put<OwnerDto>(`${this.apiUrl}/${owner.id}`, owner);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

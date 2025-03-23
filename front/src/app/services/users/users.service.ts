import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../interfaces/User';
import { UserResponse } from '../../interfaces/UserResponse';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private path: string = 'http://localhost:3001/api';

  constructor(private http: HttpClient) {}

  public get(id: number): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.path}/users/${id}`);
  }

  public getAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.path}/users`);
  }
}

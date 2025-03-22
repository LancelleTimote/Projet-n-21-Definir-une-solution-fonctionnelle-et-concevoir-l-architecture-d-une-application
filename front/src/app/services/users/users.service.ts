import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../../interfaces/User';
import { UserResponse } from '../../interfaces/UserReponse';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private path: string = 'http://localhost:3001/api/users';

  constructor(private http: HttpClient) {}

  public get(id: number): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.path}/${id}`).pipe(
      catchError((error) => {
        console.error("Erreur lors de la récupération de l'utilisateur", error);
        return throwError(
          'Impossible de récupérer les données utilisateur. Essayez à nouveau plus tard.'
        );
      })
    );
  }

  public getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.path).pipe(
      catchError((error) => {
        console.error('Erreur lors de la récupération des utilisateurs', error);
        return throwError(
          'Impossible de récupérer la liste des utilisateurs. Essayez à nouveau plus tard.'
        );
      })
    );
  }
}

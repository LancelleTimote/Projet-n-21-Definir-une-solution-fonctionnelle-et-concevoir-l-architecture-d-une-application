import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Chat } from '../../interfaces/Chat';
import { ChatRequest } from '../../interfaces/ChatRequest';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private path: string = environment.apiUrl || 'http://localhost:3001/api';

  constructor(private http: HttpClient) {}

  public get(id: number): Observable<Chat[]> {
    return this.http.get(`${this.path}/chat/${id}`).pipe(
      map((response: any) => response as Chat[]),
      catchError(this.handleError)
    );
  }

  public send(request: ChatRequest): Observable<Chat> {
    return this.http
      .post<Chat>(`${this.path}/chat`, request)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: any) {
    console.error('Erreur dans ChatService:', error);
    return throwError(error);
  }
}

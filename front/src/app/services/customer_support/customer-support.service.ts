import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CustomerSupport } from '../../interfaces/CustomerSupport';

@Injectable({
  providedIn: 'root',
})
export class CustomerSupportService {
  private path: string = 'http://localhost:3001/api';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<CustomerSupport[]> {
    return this.http
      .get<CustomerSupport[]>(`${this.path}/customer_support`)
      .pipe(
        catchError((error) => {
          console.error('Error fetching customer support list', error);
          return throwError(error);
        })
      );
  }
}

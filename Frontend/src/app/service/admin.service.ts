import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Admin } from '../model/admin.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAdminInfo(token: string): Observable<Admin> {
    let header = {
      'Authorization' : 'Basic ' + token
    }
    return this.http.get<Admin>(environment.serverUrl + '/admin/details', {headers: header});
  }
}

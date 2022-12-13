import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Member } from '../model/member.model';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private http: HttpClient) { }

  getMemberInfo(token: string): Observable<Member> {
    let header = {
      'Authorization' : 'Basic ' + token
    }
    return this.http.get<Member>(environment.serverUrl + '/member/details', {headers: header});
  }
}

import { TestBed } from '@angular/core/testing';

import { AddplanService } from './addplan.service';

describe('AddplanService', () => {
  let service: AddplanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddplanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

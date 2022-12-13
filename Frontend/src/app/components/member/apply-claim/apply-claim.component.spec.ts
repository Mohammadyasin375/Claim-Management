import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyClaimComponent } from './apply-claim.component';

describe('ApplyClaimComponent', () => {
  let component: ApplyClaimComponent;
  let fixture: ComponentFixture<ApplyClaimComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplyClaimComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplyClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

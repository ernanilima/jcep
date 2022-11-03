import { HttpResponse } from '@angular/common/http';
import { ResponseSpringBoot } from './response-spring-boot';

export class Page<T> {
  constructor(
    public content: T[],
    public totalElements: number
  ) {}

  public static fromResponse<T>(response: HttpResponse<ResponseSpringBoot>): Page<T> {
    return new Page<T>(response.body.content, response.body.size);
  }
}

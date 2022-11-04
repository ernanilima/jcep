import { HttpResponse } from '@angular/common/http';

import { ResponseSpringBoot } from './response-spring-boot';

export interface PageQuery {
  pageNumber: number,
  pageSize: number
}

export interface QueryBuilder {
  pageQuery: PageQuery;
  buildQueryMap(): Map<string, string>;
  buildQueryString(): string;
  buildPageQueryMap(): Map<string, string>;
}

export class PageRequest implements QueryBuilder {

  constructor(
    public pageQuery: PageQuery
  ) {}

  public static of(pageQuery: { pageSize: number }): PageRequest {
    return new PageRequest({ pageNumber: 0, pageSize: pageQuery.pageSize});
  }

  public buildQueryMap(): Map<string, string> {
    return  new Map<string, string>([...this.buildPageQueryMap()]);
  }

  public buildPageQueryMap(): Map<string, string> {
    const buildPageQueryMap = new Map<string, string>();
    buildPageQueryMap.set('page', `${this.pageQuery.pageNumber}`);
    buildPageQueryMap.set('size', `${this.pageQuery.pageSize}`);
    return buildPageQueryMap;
  }

  public buildQueryString(): string {
    return Array.from(this.buildQueryMap()).map((item: string[]) => `${item[0]}=${item[1]}`).join('&');
  }
}

export class Page<T> {
  constructor(
    public content: T[],
    public totalElements: number
  ) {}

  public static fromResponse<T>(response: HttpResponse<ResponseSpringBoot>): Page<T> {
    return new Page<T>(response.body.content, response.body.size);
  }
}

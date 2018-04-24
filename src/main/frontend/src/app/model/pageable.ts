export class Pageable<T> {
  content: T[];
  numberOfElements: number;
  totalElements: number;
  first: boolean;
  last: boolean;
  totalPages: number;
  size: number;
  number: number;
}

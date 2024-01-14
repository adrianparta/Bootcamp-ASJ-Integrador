import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filterPipe'
})

export class filterPipePipe implements PipeTransform{
    transform(value: any[], args?: any):any{
        if(args === '' || args === undefined){
            return value;
        }
        return value.filter((item) =>
            JSON.stringify(item).toLowerCase().includes(args.toLowerCase()));
    }

    
}
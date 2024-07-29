package example.day10;

import java.util.Arrays;

public class MyList < T > {
    private int size;
    private T[] array;

    public MyList(){  this.array = (T[]) new Object[ this.size ];  }

    public boolean add( T value ){
        // [1] 사이즈 증가
        this.size++;
        // [2] 증가된 사이즈로 새로운 배열( 기존배열에 사이즈를 추가가 아닌 교체 형식으로 구현)
        T[] newArray = (T[])(new Object[ this.size ]);
        // [3] 기존 배열의 요소들을 새로운 배열에 대입
        for( int i = 0 ; i < this.array.length ; i++ ){
            newArray[i] = this.array[i];
        }
        // [4] 기존배열보다 새로운배열이 사이즈가 1개 더 크기 때문에 마지막인덱스에 매개변수 대입
        newArray[ size - 1 ] = value;
        // [5] 새로운배열에는 기존요소와 새로운요소 들어있는 상태를 기본배열에 대입
        this.array = newArray;
        return true;
    }

    public boolean remove( int index ){
        // 존재하지 않는 인덱스가 있으면 false 반환
        if( index > this.array.length ){return false;}
        // ---
        // [1] 사이즈 감소
        this.size--;
        // [2] 감소된 사이즈로 새로운 배열
        T[] newArray = (T[])(new Object[ this.size ]);
        // [3] 기존 배열의 요소들을 새로운 배열에 대입
        int j = 0; // j:새로운 배열의 인덱스
        // i : 기존 배열의 인덱스
        for( int i = 0 ; i<this.array.length ; i++ ){
            // - 삭제할 인덱스는 제외 하고 복사
            if( i == index ) { continue; } // 반복문(증감식) 으로 이동
            newArray[j] = this.array[i];
            j++; // 새로운 배열의 인덱스 증가.
        }
        // [4]
        // [5]
        this.array = newArray;
        return true;
    }
    public T get( int index ){
        // 존재하지 않는 인덱스가 있으면 null 반환
        if( index > this.array.length ){return null;}
        return this.array[index];
    }

    @Override
    public String toString() {
        return "MyList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}

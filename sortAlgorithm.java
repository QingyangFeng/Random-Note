import java.util.Arrays;

public class sortAlgorithm {
        //常用的8种排序算法

        //冒泡排序 时间复杂程度O(n^2)
        //比较相邻的元素。如果第一个比第二个大，就交换他们两个。
        //对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
        //针对所有的元素重复以上的步骤，除了最后一个。
        //持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
        public static void bubbleSort(int[] a){
            int temp = 0;
            for (int i=0;i<a.length-1;i++){
                for(int j=0;j<a.length-1-i;j++){
                    if(a[j]>a[j+1]){
                        temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] =temp;
                    }
                }

            }
        }


        public static void swap(int[] a,int p, int q){
            int temp = a[p];
            a[p] = a[q];
            a[q] = temp;
        }

        //选择排序 时间复杂程度O(n^2)
        //从待排序序列中，找到关键字最小的元素；
        //如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
        //从余下的 N - 1 个元素中，找出关键字最小的元素，重复之前步骤，直到排序结束。
        //仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
        public static void selectSort(int[] a){
            int min = 0;
            int position = 0;
            for (int i = 0; i < a.length; i++){
                min = a[i];
                position = i;
                for(int j = i+1;j<a.length;j++){
                    if(min>a[j]){
                        min = a[j];
                        position = j;
                    }
                }
                a[position] = a[i];
                a[i] = min;
            }
        }

        //建立大顶堆
        public static void buildMaxHeap(int[] a, int lastIndex){
            for(int i= (lastIndex-1)/2;i>=0;i--){
                int k= i;
                while(k*2+1<=lastIndex){
                    int biggerIndex = k*2+1;
                    if (biggerIndex<lastIndex){
                        if(a[biggerIndex]<a[biggerIndex+1]) {
                            biggerIndex++;
                        }
                    }
                    if(a[k]<a[biggerIndex]){
                        swap(a,k,biggerIndex);
                        k=biggerIndex;
                    }
                    else
                        break;
                }
            }
        }

        //堆排序 时间复杂程度O(nlog(n))
        //先将初始序列K[1..n]建成一个大顶堆, 那么此时第一个元素K1最大, 此堆为初始的无序区.
        //再将关键字最大的记录K1 (即堆顶, 第一个元素)和无序区的最后一个记录 Kn 交换, 由此得到新的无序区K[1..n−1]和有序区K[n], 且满足K[1..n−1].keys⩽K[n].key
        //交换K1 和 Kn 后, 堆顶可能违反堆性质, 因此需将K[1..n−1]调整为堆. 然后重复上一步, 直到无序区只有一个元素时停止.
        public static void heapSort(int[] a, int lastIndex){
            for(int i=0;i<a.length-1;i++){
                buildMaxHeap(a,lastIndex-1-i);
                swap(a,0,lastIndex-1-i);
            }
        }

        //获取中间值
        public static int getMiddle(int[] a, int low, int high){
            int tmp = a[low];
            while(low<high){
                while(low<high&&tmp<a[high]) {
                    high--;
                }
                a[low] = a[high];
                while(low<high&&tmp>a[low]) {
                    low++;
                }
                a[high]=a[low];
            }
            a[low]=tmp;
            return low;
        }

        //快速排序 时间复杂程度O(nlog(n))
        //快速排序从小到大排序：在数组中随机选一个数（默认数组首个元素），
        //数组中小于等于此数的放在左边，大于此数的放在右边，再对数组两边递归调用快速排序，重复这个过程。
        public static void quickSort(int[] a, int low, int high){
            if(low<high) {
                int middle = getMiddle(a, low, high);
                quickSort(a, low, middle-1);
                quickSort(a, middle + 1, high);
            }
        }

        public static int[] mergeTwoArray(int[] left, int[] right){
            int[] arr = new int[right.length+left.length];
            int i=0,j=0,k = 0;
            while(i<right.length&&j<left.length){
                if(right[i]<left[j])
                    arr[k++]=right[i++];
                else
                    arr[k++]=left[j++];
            }
            while(i<right.length){
                arr[k++]=right[i++];
            }
            while(j<left.length){
                arr[k++]=left[j++];
            }
            System.out.println("Merging: " + Arrays.toString(arr));
            return arr;
        }

        //归并排序 时间复杂程度O(nlog(n))
        //首先让数组中的每一个数单独成为长度为1的区间，然后两两一组有序合并，得到长度为2的有序区间，依次进行，直到合成整个区间.
        public static int[] mergeSort(int[] a){
            if (a.length <= 1) return a;

            int length = a.length>>1;
            int[] left= Arrays.copyOfRange(a,0,length);
            int[] right = Arrays.copyOfRange(a,length,a.length);
            System.out.println("After: " + Arrays.toString(left) + "+" + Arrays.toString(right));
            a = mergeTwoArray(mergeSort(left),mergeSort(right));
            System.out.println("Before: " + Arrays.toString(a));
            return a;

        }


        //插入排序 时间复杂程度O(n^2)
        //插入排序从小到大排序：首先位置1上的数和位置0上的数进行比较，如果位置1上的数大于位置0上的数，将位置0上的数向后移一位，
        //将1插入到0位置，否则不处理。位置k上的数和之前的数依次进行比较，如果位置K上的数更大，将之前的数向后移位，
        //最后将位置k上的数插入不满足条件点，反之不处理。
        public static void insertSort(int[] a){
            int tmp=0;
            for(int i=1;i<a.length;i++){
                tmp=a[i];
                int j= i-1;
                for(;j>=0&&a[j]>tmp;j--){
                        a[j+1]=a[j];
                }
                a[j+1]=tmp;

            }
        }

        //希尔排序 时间复杂程度O(nlog(n))
        //希尔排序是插入排序改良的算法，希尔排序步长从大到小调整，
        //第一次循环后面元素逐个和前面元素按间隔步长进行比较并交换，直至步长为1.
        public static void shellSort(int[] a){
            int d = a.length;
            while(true){
                d=(int)Math.ceil(d/2);
                for(int i=0;i<d;i++){

                    for(int x=i+d;x<a.length;x+=d){
                        int j = x-d;
                        int tmp=a[x];
                        for (; j >= 0 && a[j] > tmp; j -= d) {
                            a[j + d] = a[j];
                        }
                        a[j + d] = tmp;
                    }
                }
                if(d==1) break;
            }
        }

        //基数排序 时间复杂程度O(n*数据最大位数)
        //此处为LSD--least significant digital first 从个位开始排序直至最高位
        public static void radixSort(int[] arr){
            if(arr.length <= 1) return;

            //取得数组中的最大数，并取得位数
            int max = 0;
            for(int i = 0; i < arr.length; i++){
               if(max < arr[i]){
                  max = arr[i];
               }
            }
            int maxDigit = 1;
            while(max / 10 > 0){
                maxDigit++;
            max = max / 10;
            }
            System.out.println("maxDigit: " + maxDigit);

            //申请一个桶空间
            int[][] buckets = new int[10][arr.length];
            int base = 10;

            //从低位到高位，对每一位遍历，将所有元素分配到桶中
            for(int i = 0; i < maxDigit; i++){
                int[] bktLen = new int[10];        //存储各个桶中存储元素的数量

               //分配：将所有元素分配到桶中
               for(int j = 0; j < arr.length; j++){
                   int whichBucket = (arr[j] % base) / (base / 10);
                   buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                   bktLen[whichBucket]++;
            }

            //收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
                int k = 0;
                for(int b = 0; b < buckets.length; b++){
                    for(int p = 0; p < bktLen[b]; p++){
                        arr[k++] = buckets[b][p];
                    }
                }

                System.out.println("Sorting: " + Arrays.toString(arr));
                base *= 10;
            }
        }

        public static void main(String args[]){
            int a[] ={13,5,6,7,22,3};
            //bubbleSort(a);
            //selectSort(a);
            //heapSort(a,a.length);
            //quickSort(a,0,a.length-1);
            //a = mergeSort(a);
            //insertSort(a);
            //shellSort(a);
            radixSort(a);
            System.out.println("Result: " + Arrays.toString(a));

        }

}

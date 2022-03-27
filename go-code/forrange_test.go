package gocode

import (
	"testing"
)

type Data [1024]byte

func BenchmarkForStruct_test(b *testing.B) {
	var items [1024]Data
	var result Data
	for i := 0; i < b.N; i++ {
		for k := 0; k < len(items); k++ {
			result = items[k]
		}
	}
	_ = result
}

func BenchmarkRangeStruct_test(b *testing.B) {
	var items [1024]Data
	var result Data
	for i := 0; i < b.N; i++ {
		for _, item := range items {
			result = item
		}
	}
	_ = result
}

func BenchmarkRangeStructOptimize_test(b *testing.B) {
	var items [1024]Data
	var result Data
	for i := 0; i < b.N; i++ {
		for k, _ := range items {
			result = items[k]
		}
	}
	_ = result
}

func BenchmarkForR_test(b *testing.B) {
	var items [1024]byte
	var result byte
	for i := 0; i < b.N; i++ {
		for k := 0; k < len(items); k++ {
			result = items[k]
		}
	}
	_ = result
}

func BenchmarkRangeR_test(b *testing.B) {
	var items [1024]byte
	var result byte
	for i := 0; i < b.N; i++ {
		for _, item := range items {
			result = item
		}
	}
	_ = result
}

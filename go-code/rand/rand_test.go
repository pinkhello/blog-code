package gocode

import (
	"math/rand"
	"testing"
	"time"
)

func BenchmarkGlobalRand_test(b *testing.B) {
	b.RunParallel(func(p *testing.PB) {
		for p.Next() {
			rand.Intn(200)
		}
	})
}

func BenchmarkCustomRand_test(b *testing.B) {
	b.RunParallel(func(p *testing.PB) {
		rd := rand.New(rand.NewSource(time.Now().Unix()))
		for p.Next() {
			rd.Intn(200)
		}
	})
}

func BenchmarkFrandWithConcurrent_test(b *testing.B) {
	rd := New()
	b.RunParallel(func(pb *testing.PB) {
		for pb.Next() {
			rd.Intn(200)
		}
	})

}

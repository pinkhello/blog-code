# ent 使用 demo

# project 目录下初始化 schema

```shell
> ent init --help
Usage:
  ent init [flags] [schemas]

Examples:
  ent init Example
  ent init --target entv1/schema User Group
```

在 `spec/schema` 下初始化 `User`

> `ent init --target spec/schema User`

# 在 `spec/schema/user.go` 增加字段或者其他
```go
	return []ent.Field{
		field.Int("age").Positive(),
		field.String("name").Default("-"),
	}
```

# 创建 codegen 目录 `gen/entschema`, 并创建 `module.go`描述模块
```go
package entschema

```

# 生成代码

```shell
> ent generate --help 
generate go code for the schema directory

Usage:
  ent generate [flags] path

Examples:
  ent generate ./ent/schema
  ent generate github.com/a8m/x

Flags:
      --feature strings                         extend codegen with additional features
      --header string                           override codegen header
  -h, --help                                    help for generate
      --idtype [int int64 uint uint64 string]   type of the id field (default int)
      --storage string                          storage driver to support in codegen (default "sql")
      --target string                           target directory for codegen
      --template strings                        external templates to execute
```

生成代码命令 代码目录生成在 `gen/entschema` 模块下

> `ent generate --feature sql/upsert --target ./gen/entschema spec/schema`



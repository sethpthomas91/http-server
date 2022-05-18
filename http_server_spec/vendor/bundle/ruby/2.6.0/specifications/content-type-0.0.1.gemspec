# -*- encoding: utf-8 -*-
# stub: content-type 0.0.1 ruby lib

Gem::Specification.new do |s|
  s.name = "content-type".freeze
  s.version = "0.0.1"

  s.required_rubygems_version = Gem::Requirement.new(">= 0".freeze) if s.respond_to? :required_rubygems_version=
  s.require_paths = ["lib".freeze]
  s.authors = ["Aleksey V Zapparov".freeze]
  s.date = "2014-01-26"
  s.description = "ContentType parser".freeze
  s.email = ["ixti@member.fsf.org".freeze]
  s.homepage = "https://github.com/ixti/content-type".freeze
  s.licenses = ["MIT".freeze]
  s.rubygems_version = "3.0.3.1".freeze
  s.summary = "content-type-0.0.1".freeze

  s.installed_by_version = "3.0.3.1" if s.respond_to? :installed_by_version

  if s.respond_to? :specification_version then
    s.specification_version = 3

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<parslet>.freeze, ["~> 1.5"])
      s.add_development_dependency(%q<bundler>.freeze, ["~> 1.5"])
    else
      s.add_dependency(%q<parslet>.freeze, ["~> 1.5"])
      s.add_dependency(%q<bundler>.freeze, ["~> 1.5"])
    end
  else
    s.add_dependency(%q<parslet>.freeze, ["~> 1.5"])
    s.add_dependency(%q<bundler>.freeze, ["~> 1.5"])
  end
end
